import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ECISpringBoot {

    //Map encargado de mantener los pares <Url, método>
    private Map<String, Method> services = new HashMap<>();

    private static ECISpringBoot singleton = new ECISpringBoot();

    private ECISpringBoot(){};

    public static ECISpringBoot getInstance(){
        return singleton;
    }

    public void startServer(){
        loadComponents();
        try {
            HttpServer server = new HttpServer();
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadComponents() {
        String[] componentList = searchComponentList();
        for (String t : componentList){
            loadServices(t);
        }
    }

    private void loadServices(String t) {
        try {
            Class c = Class.forName(t);
            Method[] declared = c.getDeclaredMethods();
            for (Method m : declared){
                if (m.isAnnotationPresent(Service.class)){
                    Service a = m.getAnnotation(Service.class);
                    services.put(a.value(),m);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String[] searchComponentList() {
        //return new String[]{"Debería ir a buscar en el disco duro los componentes"}
        //Método a implementar
        return null;
    }

    public String invokeService(String serviceName){
        Method serviceMethod = services.get(serviceName);
        try {
            return (String) serviceMethod.invoke(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return "Service Error lmao";
    }
}
