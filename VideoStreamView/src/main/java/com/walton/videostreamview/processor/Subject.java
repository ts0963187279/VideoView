package com.walton.videostreamview.processor;

import java.io.File;
public interface Subject{
    public void updateObservers(File file);
    public void addObserver(Observer observer);
    public void deleteObserver(Observer observer);
}
