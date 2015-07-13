package com.zhy.blogcodes.dl;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class DLProxyActivity extends Activity
{

    public static final String KEY_APK_PATH = "apk_path";
    public static final String KEY_APK_RUNNTIME_ACTIVITY = "activity_class";

    private static final String TAG = "DLProxyActivity";
    private static final String KEY_FROM_EXTERNAL = "from_external";
    private static final int VALUE_FROM_EXTERNAL = 0x110;

    private String mApkPath;
    private String mTargetActivityClazz;

    private DexClassLoader mDexClassLoader;
    private AssetManager mAssetManager;
    private Resources mResources;
    private Resources.Theme mTheme;

    @Override
    public Resources.Theme getTheme()
    {
        return mTheme == null ? super.getTheme() : mTheme;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        mApkPath = getIntent().getStringExtra(KEY_APK_PATH);
        mTargetActivityClazz = getIntent().getStringExtra(KEY_APK_RUNNTIME_ACTIVITY);

        checkApkPath();
        Log.e(TAG, mApkPath);

        initAssetAndResources();

        if (mTargetActivityClazz == null)
        {
            launchTargetActivity();
        } else
        {
            launchTargetActivity(mTargetActivityClazz);
        }


    }

    private void initAssetAndResources()
    {
        try
        {
            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            addAssetPath.setAccessible(true);
            addAssetPath.invoke(assetManager, mApkPath);
            mAssetManager = assetManager;

            Resources superRes = super.getResources();
            Resources resources = new Resources(mAssetManager,
                    superRes.getDisplayMetrics(), superRes.getConfiguration());
            mResources = resources;

            mTheme = mResources.newTheme();
            mTheme.setTo(super.getTheme());

            Log.e(TAG, mAssetManager + " ," + mResources);


        } catch (InstantiationException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        } catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }

    }

    private void checkApkPath()
    {
        if (TextUtils.isEmpty(mApkPath))
        {
            throw new IllegalArgumentException("apkPath不能为空！");
        }

        String apkPath = Environment.getExternalStorageDirectory() + mApkPath;
        //String apkPath = "/storage/emulated/legacy" + mApkPath;
        Log.e(TAG, "apkPath = " + apkPath);
        File file = new File(apkPath);
        if (!file.exists())
            throw new RuntimeException("apkPath的路径不正确！" + apkPath);

        mApkPath = apkPath;
    }

    private void launchTargetActivity(String activityClazz)
    {
        try
        {
            ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
            //init dexClassLoader
            DexClassLoader dexClassLoader = new DexClassLoader(mApkPath,
                    getDir("dexPath", Context.MODE_PRIVATE).getAbsolutePath(),
                    null,
                    localClassLoader);
            mDexClassLoader = dexClassLoader;

            Log.e(TAG, "mDexClassloader = " + mDexClassLoader);

            //get act instance
            Class<?> actClazz = dexClassLoader.loadClass(activityClazz);
            Log.e(TAG, "actClazz = " + actClazz);
            Object activityInstance = actClazz.getConstructor().newInstance();
            Log.e(TAG, "activityInstance = " + activityInstance);
            for (Method m : actClazz.getMethods())
            {
                //  Log.e(TAG, m.getName());
            }
            //invoke setActivityProxy method
            Method methodSetProxy = actClazz.getMethod("setActivityProxy", new Class[]{Activity.class});
            Log.e(TAG, "methodSetProxy = " + methodSetProxy);
            methodSetProxy.setAccessible(true);
            methodSetProxy.invoke(activityInstance, this);
            //invoke onCreate
            Method onCreate = actClazz.getDeclaredMethod("onCreate",
                    new Class[]{Bundle.class});
            onCreate.setAccessible(true);

            Log.e(TAG, "onCreate = " + onCreate);
            Bundle bundle = new Bundle();
            bundle.putInt(KEY_FROM_EXTERNAL, VALUE_FROM_EXTERNAL);
            onCreate.invoke(activityInstance, new Object[]{bundle});


        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        } catch (InvocationTargetException e)
        {
            e.printStackTrace();
        } catch (InstantiationException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }

    }


    private void launchTargetActivity()
    {

        //getPackageManager for activity
        PackageManager pm = getPackageManager();
        PackageInfo packageInfo = pm.getPackageArchiveInfo(mApkPath, PackageManager.GET_ACTIVITIES);
        ActivityInfo[] activities = packageInfo.activities;

        if (activities != null && activities.length > 0)
        {
            String activityClazz = activities[0].name;
            Log.e(TAG, "ActivityClazz = " + activityClazz);
            mTargetActivityClazz = activityClazz;
            launchTargetActivity(activityClazz);
        }
    }


    @Override
    public ClassLoader getClassLoader()
    {
        return mDexClassLoader;
    }

    @Override
    public AssetManager getAssets()
    {
        return mAssetManager == null ? super.getAssets() : mAssetManager;
    }

    @Override
    public Resources getResources()
    {
        return mResources == null ? super.getResources() : mResources;
    }
}
