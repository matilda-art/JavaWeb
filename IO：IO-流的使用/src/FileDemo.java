import java.io.File;
import java.io.IOException;

/**
 * @program: IO：IO流的使用
 * @description
 * 初始IO：
 * 关于文件的理解：硬盘数据的抽象
 * 文件：元数据+内容信息
 * 文件路径：绝对路径 vs 相对路径
 * 文件路径以树形结构组织
 * 文件分为：目录 vs 普通文件
 *
 * java中表示文件的类 —— File(元数据) —— 也可以表示不存在的文件
 * 1.创建一个File对象
 *  1.根据路径（绝对路径vs相对路径）创建
 *    相对路径：相对于应用启动时所在的路径——和写代码时候的路径完全没有关系
 *  2.提供父对象+路径
 * 2.获取文件的一些属性
 *  isXXX() or getXXX() 把这些属性和真正文件的属性有对应关系
 * 3.操作
 *  1.创建
 *      1.创建普通文件 createNewFile() 带内容，目前内容是空的
 *      2.创建目录    mkdir() or mkdirs()
 *  2.关于文件的操作
 *      1.文件重命名
 *      2.剪切+粘贴：久路径 -> 新路径
 *      3.文件的复制粘贴，因为涉及到内容部分，不是通过File可以简单处理
 *          1.普通文件有自己的方法
 *          2.目录有自己的方法
 *      4.文件的删除delete()（删除只允许删除树上的叶子节点）
 *          1.普通文件的删除（文件中是否包含内容完全没有影响）
 *          2.目录的删除（必须是一个空目录（目录下没有其他文件的目录））
 *          强调：这里的删除是彻底删除，不是进入到“回收站”的删除
 *      5.关于目录的遍历：
 *          1.提供的基本操作：查看目录下的孩子有哪些
 *          2.完整的遍历一个目录（包含所有子孙结点）
 *          （默认的顺序的名字顺序）
 *          —— 指定根，进行树的遍历
 *              1.深度优先的遍历（前/中/后序）<-递归（栈）
 *              2.广度优先的遍历（层序）<-队列
 *
 *      renameTo()
 *      1.模拟重命名
 *      2.模拟剪切粘贴
 *      3.如果目标文件已经存在：（无法进行重命名）
 *          1.普通文件
 *          2.目录
 *
 * @author: matilda
 * @create: 2020-06-25 10:20
 **/
public class FileDemo {
    public static void main(String[] args) {
        //先使用绝对路径
        //1.对应一个实际存在的文件
        //2.对应一个实际不存在的文件
        //3.对应一个实际存在的目录
        //String path = "E:\\代码\\Javaweb\\IO-IO流的使用\\测试目录";
        String path = "测试目录2\\hello.txt";

        //构建文件对象
        File file = new File(path);//路径对应的文件，但文件可能实际上不存在

        //常见属性
        //文件存在 && 普通文件 ->返回true
        System.out.println(file.isFile());
        //文件存在 && 是文件夹 ->返回true
        System.out.println(file.isDirectory());
        System.out.println(file.isAbsolute());
        System.out.println(file.isHidden());
        System.out.println(file.exists());
        System.out.println(file.getAbsolutePath());
        System.out.println(file.getPath());
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.canRead());
        System.out.println(file.canWrite());
        System.out.println(file.canExecute());


        //测试目录2\hello.txt
        //要创建hello.txt文件，要求创建目录2首先存在
        //但这里测试目录2不存在，所以会出错
            //1.演示创建成功
            //2.演示文件已存在
            //3.上一级目录都不存在
       /* try {
            boolean newFile = file.createNewFile();//普通文件
            System.out.println(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

/*        boolean mkdir = file.mkdir();
        System.out.println(mkdir);*/

        System.out.println("会把中间没有的目录，循环创建创建出来");
        boolean mkdirs = file.mkdirs();
        System.out.println(mkdirs);
    }
}

//关于文件的操作：
//1.创建新文件：
//  普通文件：createNewFile
//          1.可以成功创建：创建并返回true
//          2.文件已存在：直接返回false
//          3.创建失败：抛出IOException
//  目录：mkdir()
//          1.可以成功创建：创建并返回true
//          2.文件已存在/创建失败：直接返回false

//          mkdirs()：连同中间不存在的目录，一并创建出来