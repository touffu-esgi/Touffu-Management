package cat.touffu.management.plugin;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class PluginHandler {
    private List<File> jars = List.of();
    private List<JavaFxPlugin> plugins = List.of();
    private JavaFxPlugin current = null;

    private void loadPluginFiles() throws IOException {
        final String pluginDirectoryPath = "plugins";
        File directory = new File(pluginDirectoryPath);
        if (!(directory.exists() || directory.mkdirs())) {
            throw new IOException("Unable to access or create Plugins directory.");
        }
        var plugins = directory.listFiles((file, name)->name.endsWith(".jar"));
        this.jars = plugins != null
                ? List.of(plugins)
                : List.of();
    }

    public void loadPlugins() throws IOException {
        this.loadPluginFiles();
        this.plugins = this.jars.stream()
                .map(this::loadPlugin)
                .toList();
    }

    private JavaFxPlugin loadPlugin(File plugin) {
        try {
            var url = new URL("file:///" + plugin.getAbsolutePath());
            var loader = new URLClassLoader(new URL[]{url}, getClass().getClassLoader());

            var pluginClass = Class.forName("Plugin", true, loader);
            return (JavaFxPlugin)pluginClass.getConstructor().newInstance();
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        return null;
    }

    public void displayPluginOnView(JavaFxPlugin plugin, Pane view) {
        try {
            view.getChildren().add(plugin.getView().load());
            this.current = plugin;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void selectPluginByName(String name) {
        this.current = this.plugins.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElseThrow(()-> new RuntimeException("Unknown Plugin Name"));
    }

    public void displayCurrentPlugin(Pane view) {
        this.displayPluginOnView(this.current, view);
    }

    public List<JavaFxPlugin> getPlugins() {
        return plugins;
    }
}
