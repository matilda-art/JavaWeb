import java.io.File;

/**
 * @program: IO：IO流的使用
 * @description
 *
 * 深度优先遍历 —— 递归
 * @author: matilda
 * @create: 2020-06-25 14:24
 **/
public class ScanDirDepth {
    public static void main(String[] args) {
        File root = new File("测试目录\\非空目录");

        scanDir(0,root);
    }

    //保证node代表的一定是目录
    private static void scanDir(int level,File node){
        //1.先打印结点的绝对路径
        //  假设我的缩进单位是4个空格，
        for (int i = 0; i < level; i++) {
            System.out.println("    ");
        }
        System.out.println(node.getAbsolutePath() + "\\");
        //2.获取结点的孩子结点
        File[] children = node.listFiles();
        if (children == null){
            //防御式编程：理论上不应该出现该情况的
            return;
        }

        //3.孩子中的普通文件，直接打印
        //  孩子中的目录，递归，进行深度遍历
        //  隐含的递归停止条件：children的长度 == 0的时候，下面的循环没有任何效果
        for (File child:children) {
            if (child.isDirectory()){
                scanDir(level+1,child);
            }else if (child.isFile()){
                System.out.println(child.getAbsolutePath());
            }
        }
    }
}
