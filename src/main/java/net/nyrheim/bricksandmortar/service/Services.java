package net.nyrheim.bricksandmortar.service;

import net.nyrheim.bricksandmortar.BricksAndMortar;
import net.nyrheim.bricksandmortar.exception.UnregisteredServiceException;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;

public final class Services {
    private final BricksAndMortar plugin;

    public Services(BricksAndMortar plugin) {
        this.plugin = plugin;
    }

    public <T> T get(Class<T> serviceClass) {
        RegisteredServiceProvider<T> serviceProvider = this.plugin.getServer().getServicesManager().getRegistration(serviceClass);
        if (serviceProvider == null) {
            throw new UnregisteredServiceException(serviceClass);
        } else {
            return serviceProvider.getProvider();
        }
    }

    public <T> void register(Class<T> serviceClass, T service) {
        this.register(serviceClass, service, ServicePriority.Normal);
    }

    public <T> void register(Class<T> serviceClass, T service, ServicePriority priority) {
        this.plugin.getServer().getServicesManager().register(serviceClass, service, this.plugin, priority);
    }
}