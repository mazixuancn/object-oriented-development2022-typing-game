package tool;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;


public class TxtFileChooser extends JFrame {
    TxtFilter txtFilter;
    JFileChooser chooser;
    private int flag;
    public TxtFileChooser() throws HeadlessException {
        super("选择文本文件");
        setSize(500,300);
        setLocationRelativeTo(null);
        setUndecorated(true);
        txtFilter= new TxtFilter();
        chooser =new JFileChooser();
        chooser.setFileFilter(txtFilter);
        flag=chooser.showOpenDialog(this);

    }
    public int getFlag() {
        return flag;
    }
    //判断是否为txt
    public boolean isTxt(){
        return chooser.getSelectedFile().getName().endsWith(".txt");
    }

    public File getSelectTxt(){
        return chooser.getSelectedFile();
    }

    static class TxtFilter extends FileFilter{

        @Override
        public boolean accept(File file) {
            String name=file.getName();
            return file.isDirectory()||name.toLowerCase().endsWith(".txt");
        }

        @Override
        public String getDescription() {
            return "*.txt";
        }
    }
}
