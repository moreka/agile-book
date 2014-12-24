import play.Configuration;
import play.GlobalSettings;
import play.Mode;
import play.mvc.Action;
import play.mvc.Http;

import java.io.File;
import java.lang.reflect.Method;

/**
 * Created by root on 12/23/14.
 */
public class Global extends GlobalSettings {

    @Override
    public Configuration onLoadConfig(Configuration configuration, File file, ClassLoader classLoader) {
        return super.onLoadConfig(configuration, file, classLoader);
    }

    @Override
    public Action onRequest(Http.Request request, Method method) {
        return super.onRequest(request, method);
    }
}
