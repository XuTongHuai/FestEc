package com.xutonghuai.festec.example;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.EntypoModule;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.joanzapata.iconify.fonts.IoniconsModule;
import com.joanzapata.iconify.fonts.MaterialCommunityModule;
import com.joanzapata.iconify.fonts.MaterialModule;
import com.joanzapata.iconify.fonts.MeteoconsModule;
import com.joanzapata.iconify.fonts.SimpleLineIconsModule;
import com.joanzapata.iconify.fonts.TypiconsModule;
import com.joanzapata.iconify.fonts.WeathericonsModule;
import com.xutonghuai.latter.app.Latter;
import com.xutonghuai.latter.ec.database.DatabaseManager;
import com.xutonghuai.latter.ec.icon.FontEcModule;
import com.xutonghuai.latter.net.interceptors.DebugInterceptor;

/**
 * Created by xutonghuai on 2018-03-14.
 */
public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latter.init(this)
                .withIcon(new FontAwesomeModule())//初始化字体
                .withIcon(new FontAwesomeModule())
                .withIcon(new EntypoModule())
                .withIcon(new TypiconsModule())
                .withIcon(new MaterialModule())
                .withIcon(new MaterialCommunityModule())
                .withIcon(new MeteoconsModule())
                .withIcon(new WeathericonsModule())
                .withIcon(new SimpleLineIconsModule())
                .withIcon(new IoniconsModule())
                .withIcon(new FontEcModule())       //自定义的icon库
                .withApiHost("http://192.168.31.105:8080/RestServer/api/")
                .withInterceptor(new DebugInterceptor("test", R.raw.test))
                .configure();

        DatabaseManager.getInstance().init(this);

        initStetho();

    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }
}
