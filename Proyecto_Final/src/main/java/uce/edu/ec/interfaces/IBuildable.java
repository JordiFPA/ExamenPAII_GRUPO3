package uce.edu.ec.interfaces;
@FunctionalInterface
public interface IBuildable {
    void build(Runnable updateProgress);
}
