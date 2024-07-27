package uce.edu.ec.model;

import uce.edu.ec.interfaces.IBuildable;
import uce.edu.ec.interfaces.ICut;
import uce.edu.ec.interfaces.IPaint;
import uce.edu.ec.interfaces.IPolishable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ManufacturingProcess {
    private final ICut cutProcess;
    private final IPaint paintProcess;
    private final IPolishable polishProcess;
    private final IBuildable buildProcess;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public ManufacturingProcess(ICut cutProcess, IPaint paintProcess, IPolishable polishProcess, IBuildable buildProcess) {
        this.cutProcess = cutProcess;
        this.paintProcess = paintProcess;
        this.polishProcess = polishProcess;
        this.buildProcess = buildProcess;
    }

    public void executeProcess(Product product) {
        System.out.println("Fabricando producto: " + product.getName());

        executorService.submit(() -> {
            cutProcess.cut();
            sleep();
        });

        executorService.submit(() -> {
            paintProcess.paint();
            sleep();
        });

        executorService.submit(() -> {
            polishProcess.polish();
            sleep();
        });

        executorService.submit(() -> {
            buildProcess.build();
            sleep(); //
            System.out.println("Producto fabricado: " + product.getName());
        });
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
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
