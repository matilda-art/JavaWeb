import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: IO：IO流的使用
 * @descriptio
 * Java将所有的输入/输出的相关数据，抽象成“流（stream）”的一个概念
 * 1.从方向区分：输入流（InputStream）vs 输出流（OutputStream）
 * 2.从数据源/数据目标：
 *     来自硬盘数据：文件输入流（FileInputStream） vs 文件输出流（FileOutputStream）
 *     来自网络数据：网络输入流
 *     来自内存（使用内存模拟IO）：
 *         ByteArrayInputStream(字节数组作为输入源)
 *         ByteArrayOutputStream(字节数组作为输出目标)
 *
 * FileInputStream来自 文字、输入、字节流
 * 文件流
 * @author: matilda
 * @create: 2020-06-25 15:37
 **/
public class FileStreamDemo {
    public static void main(String[] args) {
        inputDemo();
    }

    private static void inputDemo() {
        //构造的方式：
        //1.参数是File对象
        //2.参数是String类型的路径

        //可以转化为try-with-resource的形式
        InputStream is1 = null;
        try {
            is1 = new FileInputStream("测试目录\\a.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (is1 != null) {
                    is1.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //以后基本采用这种形式
        //FileInputStream 就是 InputStream的一个子类
        //InputStream 就是一种输入字节流
        //FileInputStream是一种输入字节来自文件流
        /*try(InputStream is = new FileInputStream("测试目录\\a.txt")){
            //返回数据流中的下一个字节
            //为什么要返回int，因为返回值需要返回-1，这个和真正的数据作区分
            //通过返回-1，通知已经读到文件结尾了
            //-1被称为EOS(End Of Stream)

            //统计读到的有效字节数
            int count = 0;
            while (true) {
                int b = is.read();
                if (b == -1){
                    //代表文件的内容全部读完了
                    //所以退出循环
                    break;
                }
                count++;
                System.out.printf("%d%n",b);
                System.out.printf("%c%n",b);
                System.out.printf("%x%n",b);
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try (InputStream is = new FileInputStream("测试目录\\hello.txt")){
            byte[] buffer = new byte[8];

            int count = 0;//read的调用次数
            while (true){
                //最多一次读取8个字节，不够8个也可以，读到buffer的[0,8)
                int n = is.read(buffer);
                System.out.println("n = "+n);
                count++;
                //n代表最终读到的字节个数
                //区分：最多读8个 vs 实际读到n个
                //可能出现比要求读的个数少的情况：这种情况不代表一定就是读到EOS
                //EOS:-1
                if (n == -1){
                    break;
                }

                // 有效数据保存在 buf[0, n)
                for (int i = 0; i < n; i++) {
                    int b = buffer[i];

                    System.out.printf("%d%n",b);
                    System.out.printf("%c%n",b);
                    System.out.printf("%x%n",b);
                }

                //下面的处理是错误的
                /*if (n < 8){
                    break;
                }*/

                //is.read(buffer,0,buffer.length);
            }

            System.out.println("read()一共被调用"+count+"次");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//字节流（最原始的一种数据流）—— 没有加工过的数据