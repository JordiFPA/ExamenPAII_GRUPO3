package uce.edu.ec.model;

import uce.edu.ec.interfaces.IBuildable;
import uce.edu.ec.interfaces.ICut;
import uce.edu.ec.interfaces.IPaint;
import uce.edu.ec.interfaces.IPolishable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import uce.edu.ec.service.OrderService;
import java.util.function.Consumer;

public class ManufacturingProcess {
    private final ICut cutProcess;
    private final IPaint paintProcess;
    private final IPolishable polishProcess;
    private final IBuildable buildProcess;
    private final ExecutorService executorService = Executors.newFixedThreadPool(4); // Usar un pool de hilos
    private final OrderService orderService;
    private final Consumer<String> statusUpdateCallback;

    public ManufacturingProcess(ICut cutProcess, IPaint paintProcess, IPolishable polishProcess, IBuildable buildProcess, OrderService orderService, Consumer<String> statusUpdateCallback) {
        this.cutProcess = cutProcess;
        this.paintProcess = paintProcess;
        this.polishProcess = polishProcess;
        this.buildProcess = buildProcess;
        this.orderService = orderService;
        this.statusUpdateCallback = statusUpdateCallback;
    }

    public void executeProcess(long orderId) {
        Orden order = orderService.getOrderById(orderId);
        if (order == null) {
            statusUpdateCallback.accept("Orden no encontrada.");
            return;
        }

        // Actualizar el estado a "Fabricando"
        order.setStatus("Fabricando");
        orderService.saveOrder(order);

        // Ejecutar procesos de fabricación
        executorService.submit(() -> {
            statusUpdateCallback.accept("Cortando...");
            cutProcess.cut();
            sleep();
            statusUpdateCallback.accept("Corte completado.");
        });

        executorService.submit(() -> {
            statusUpdateCallback.accept("Pintando...");
            paintProcess.paint();
            sleep();
            statusUpdateCallback.accept("Pintura completada.");
        });

        executorService.submit(() -> {
            statusUpdateCallback.accept("Pulido...");
            polishProcess.polish();
            sleep();
            statusUpdateCallback.accept("Pulido completado.");
        });

        executorService.submit(() -> {
            statusUpdateCallback.accept("Ensamblando...");
            buildProcess.build();
            sleep(); // Simula el tiempo de procesamiento
            // Actualizar el estado a "Listo" después de fabricar
            order.setStatus("Listo");
            orderService.saveOrder(order);
            statusUpdateCallback.accept("Orden fabricada: " + order.getId());
        });
    }

    private void sleep() {
        try {
            Thread.sleep(2000); // Simula el tiempo de procesamiento
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