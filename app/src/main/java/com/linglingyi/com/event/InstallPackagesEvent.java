package com.linglingyi.com.event;

import java.io.File;

/**
 * @author: lilingfei
 * @description:
 * @date: 2019/5/6
 */
public class InstallPackagesEvent {
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public InstallPackagesEvent(File file) {
        this.file = file;
    }
}
