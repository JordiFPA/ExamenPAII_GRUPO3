package uce.edu.ec.interfaces;

@FunctionalInterface
public interface ICut {
    void cut(Runnable updateProgress);
}
