using System.Runtime.InteropServices;
using System.Text.Json.Serialization;
using Extism;
using Co.Gaffe.Proto;

namespace Plugin;
public class Program
{
    public static void Main()
    {
        // Note: a `Main` method is required for the app to compile
    }

    [UnmanagedCallersOnly(EntryPoint = "spark_udf")]
    public static int SparkUDF() {
        var bytes = Pdk.GetInput();
        
        

        return 0;
    }
}