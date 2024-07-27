package uce.edu.ec.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uce.edu.ec.interfaces.IBuildable;
import uce.edu.ec.interfaces.ICut;
import uce.edu.ec.interfaces.IPaint;
import uce.edu.ec.interfaces.IPolishable;
@Component
public class ManufacturingProcess {

    private final ICut cutProcess;
    private final IPaint paintProcess;
    private final IPolishable polishProcess;
    private final IBuildable buildProcess;

    @Autowired
    public ManufacturingProcess(ICut cutProcess, IPaint paintProcess, IPolishable polishProcess, IBuildable buildProcess) {
        this.cutProcess = cutProcess;
        this.paintProcess = paintProcess;
        this.polishProcess = polishProcess;
        this.buildProcess = buildProcess;
    }

    public void cut(Runnable updateProgress) {
        cutProcess.cut(updateProgress);
    }

    public void paint(Runnable updateProgress) {
        paintProcess.paint(updateProgress);

    }

    public void polish(Runnable updateProgress) {
        polishProcess.polish(updateProgress);

    }
    public void build(Runnable updateProgress) {
        buildProcess.build(updateProgress);
    }
}