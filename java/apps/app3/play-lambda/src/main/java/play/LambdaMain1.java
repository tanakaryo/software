package play;

public class LambdaMain1 {

    public static void main(String[] args) {
        // Runnableインタフェースを実装し、無名クラスを作成
        Runnable runner = () -> { System.out.println("Hello,world"); };
        runner.run();

        // 型推論でRunnableのインスタンスを実行
        method(()-> { System.out.println("GoodBye!"); });
    }

    public static void method(Runnable runner) {
        runner.run();
    }
}
