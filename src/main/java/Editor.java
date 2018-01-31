import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * 文本编辑器——记事本类型的应用，可以打开、编辑、保存文本文档。
 * 创建后调用show()方法来显示文本编辑器界面。
 */
public class Editor {
    JMenuItem open, save, cut, copy, paste, selectAll;
    Path path;
    Charset charset;
    JTextArea jTextArea;
    JFrame jFrame = new JFrame();

    /**
     * 创建一个文本编辑器对象并指定要打开的文件路径和文件编码
     *
     * @param path    文件路径
     * @param charset 编码
     * @throws IOException
     */
    Editor(String path, Charset charset) throws IOException {
        this.path = Paths.get(path);
        this.charset = charset;
    }

    Editor(String path) throws IOException {
        this.path = Paths.get(path);
        this.charset = Charset.defaultCharset();
    }

    /**
     * 展示文本编辑器
     *
     * @throws IOException
     */
    public void show() throws IOException {
        //初始化jframe
        initJframe();
        //显示jframe
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public void initJframe() throws IOException {
        jFrame.setTitle(this.path.getFileName().toString());
        open = new JMenuItem("打开");
        save = new JMenuItem("保存");
        cut = new JMenuItem("剪切");
        copy = new JMenuItem("复制");
        paste = new JMenuItem("粘贴");
        selectAll = new JMenuItem("全选");

        jTextArea = new JTextArea(30, 70);
        jTextArea.setFont(new Font("微软雅黑", Font.PLAIN, 16));
        showText();

        EditListener editListener = new EditListener(jTextArea);
        JMenu file, edit;
        open.addActionListener(editListener);
        save.addActionListener(editListener);
        save.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,
                java.awt.Event.CTRL_MASK));

        cut.addActionListener(editListener);
        copy.addActionListener(editListener);
        paste.addActionListener(editListener);
        selectAll.addActionListener(editListener);

        JMenuBar menuBar = new JMenuBar();
       // UIManager.put("Menu.font", new Font("微软雅黑", Font.PLAIN, 15));
        file = new JMenu("文件");
        edit = new JMenu("编辑");

        file.add(open);
        file.add(save);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);

        menuBar.add(file);
        menuBar.add(edit);

        jFrame.add(menuBar);
        jFrame.setJMenuBar(menuBar);

        jFrame.add(new JScrollPane(jTextArea));
//        jTextArea.setLineWrap(true);
//        jTextArea.setWrapStyleWord(true);
    }

    public void showText() throws IOException {
        byte[] encoded = Files.readAllBytes(path);
        String content = new String(encoded, charset);
        jTextArea.setText(content);
    }

    class EditListener implements ActionListener {
        JTextArea jTextArea;

        public EditListener(JTextArea jTextArea) {
            this.jTextArea = jTextArea;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == cut)
                jTextArea.cut();
            if (e.getSource() == paste)
                jTextArea.paste();
            if (e.getSource() == copy)
                jTextArea.copy();
            if (e.getSource() == selectAll)
                jTextArea.selectAll();
            if (e.getSource() == save) {
                try {
                    saveFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (e.getSource() == open) {
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                jfc.showDialog(new JLabel(), "选择文件");
                File file = jfc.getSelectedFile();
                path = file.toPath();
                try {
                    showText();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        public void saveFile() throws IOException {
            System.out.println("正在保存文件");
            String text = this.jTextArea.getText();
            Files.write(path, text.getBytes(charset));
        }
    }

    public static void main(String[] args) throws IOException {
        new Editor("src/test/java/input.txt").show();
    }
}    