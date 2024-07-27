package uce.edu.ec.model;
import uce.edu.ec.interfaces.IBuildable;
import uce.edu.ec.interfaces.ICut;
import uce.edu.ec.interfaces.IPaint;
import uce.edu.ec.interfaces.IPolishable;
import uce.edu.ec.service.OrderService;

import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ManufacturingProcess {
    private final ICut cutProcess;
    private final IPaint paintProcess;
    private final IPolishable polishProcess;
    private final IBuildable buildProcess;
    private final ExecutorService executorService = Executors.newFixedThreadPool(4); // Pool de hilos
    private final OrderService orderService;
    private final java.util.function.Consumer<String> statusUpdater; // Función para actualizar el estado

    public ManufacturingProcess(ICut cutProcess, IPaint paintProcess, IPolishable polishProcess, IBuildable buildProcess,
                                OrderService orderService, java.util.function.Consumer<String> statusUpdater) {
        this.cutProcess = cutProcess;
        this.paintProcess = paintProcess;
        this.polishProcess = polishProcess;
        this.buildProcess = buildProcess;
        this.orderService = orderService;
        this.statusUpdater = statusUpdater;
    }

    public void executeProcess(long orderId) {
        Orden order = orderService.getOrderById(orderId);
        if (order == null) {
            statusUpdater.accept("Orden no encontrada.");
            return;
        }

        // Actualizar el estado a "Fabricando"
        order.setStatus("Fabricando");
        orderService.saveOrder(order);

        // Ejecutar procesos de fabricación
        executorService.submit(() -> {
            updateStatus("Corte en marcha...");
            cutProcess.cut();
            sleep();
            updateStatus("Corte completado.");
        });

        executorService.submit(() -> {
            updateStatus("Pintura en marcha...");
            paintProcess.paint();
            sleep();
            updateStatus("Pintura completada.");
        });

        executorService.submit(() -> {
            updateStatus("Pulido en marcha...");
            polishProcess.polish();
            sleep();
            updateStatus("Pulido completado.");
        });

        executorService.submit(() -> {
            updateStatus("Ensamblaje en marcha...");
            buildProcess.build();
            sleep();
            // Actualizar el estado a "Listo" después de fabricar
            order.setStatus("Listo");
            orderService.saveOrder(order);
            updateStatus("Producto fabricado: " + order.getId());
        });
    }

    private void updateStatus(String status) {
        statusUpdater.accept(status);
    }

    private void sleep() {
        try {
            Thread.sleep(2000); // Simula el tiempo de procesamiento (2 segundos)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }
}
