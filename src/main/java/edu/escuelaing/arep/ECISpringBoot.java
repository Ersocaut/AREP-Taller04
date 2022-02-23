package edu.escuelaing.arep;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.escuelaing.arep.annotation.*;
import server.HttpServer;

public class ECISpringBoot {

    //Map encargado de mantener los pares <Url, método>
    private final Map<String, Method> services = new HashMap<>();

    private static ECISpringBoot singleton = new ECISpringBoot();

    private File path;

    private ECISpringBoot(){};

    private ECISpringBoot(File path){
        this.path = path;
    }

    public static ECISpringBoot getInstance(){
        return singleton;
    }

    public void startServer(){
        loadComponents();
        try {
            HttpServer server = new HttpServer();
            server.start();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void loadComponents() {
        List<String> componentList = searchComponentList(this.path);
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

    private String convertPath(String p){
        return p.replace(".java","").replace("\\",".").replace("..src.main.java.","");
    }

    private List<String> searchComponentList(File file) {
        List<String> components = new ArrayList<>();
        if (file.isDirectory()){
            for (File root : file.listFiles()){
                components.addAll(searchComponentList(root));
            }
        }else{
            if (file.getName().endsWith("java")){
                String path = convertPath(file.getPath());
                Class c = null;
                try{
                    c = Class.forName(path);
                }catch(Exception e){
                    e.printStackTrace();
                }
                if (c.isAnnotationPresent(Component.class)){
                    components.add(path);
                }
            }
        }
        return components;
    }

    public String invokeService(String serviceName){
        Method serviceMethod = services.get(serviceName);
        try {
            return (String) serviceMethod.invoke(null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return "Service Error lmao";
    }
}
