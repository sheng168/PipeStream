using System;
using System.IO;
using System.Diagnostics;
using System.Collections;

namespace Pipes
{
//	class LineLabeler : StreamReader
//	{
//		public LineLabeler(Stream stream)
//			: base(stream)
//		{
//		}
//
//		public override string ReadLine()
//		{
//			return base.ReadLine();
//		}
//
//		public override async string ReadLineAsync()
//		{			
//			return await base.ReadLineAsync();
//		}
//	}

    class StopWatch: Stopwatch, IDisposable
    {
        #region IDisposable implementation

        public void Dispose()
        {
            this.Stop();
            Console.WriteLine(label + ": " + this.Elapsed);
        }

        #endregion

        string label;

        public StopWatch(string label)
        {
            this.label = label;
            this.Start();
        }
    }

	class MainClass
	{
		public static void Main(string[] args)
		{
			Console.WriteLine("Hello World!");

            var envs = System.Environment.GetEnvironmentVariables();

            using (new StopWatch("env"))
            {
                foreach (var v in envs)
                {
                    var pair = (DictionaryEntry)v;
                    Console.WriteLine(pair.Key + " " + pair.Value);
                }
            }

            var noop = new CountDecorator<int>()
            {
                Target = new NoopTarget<int>()
            };

            var sw = new StopWatch("loop time");
            using (sw)
            {
                for (int i = 0; i < 10000000; i++)
                {
                    noop.On(i);    
                }
            }
//            Console.WriteLine("start " + start);
//            Console.WriteLine("done " + now);
            var totalSeconds = sw.Elapsed.TotalSeconds; // now.Subtract(start).TotalSeconds;

            Console.WriteLine(noop.Count);
            Console.WriteLine("/");
            Console.WriteLine(totalSeconds);

            Console.WriteLine(noop.Count / totalSeconds / 1e6 + "m/sec");


//            var p1 = Process.Start("/Users/jyu/OneDrive - Catchpoint Systems/3genlabs/code/workspace/jyu/web-driver/CommandLine/CommandLine/clean_storage.sh");
//            var p1 = Process.Start("rm", "-rv /Library/Caches/com.apple.dt.instruments");
            var p1 = Process.Start("./clean.sh");
            p1.WaitForExit();


//			p.StandardError;

			var console = new ConsoleTarget();

			foreach (var arg in args)
			{
				var psi = new ProcessStartInfo("ping", arg)
				{
					UseShellExecute = false,
					RedirectStandardOutput = true,
					RedirectStandardError = true
				};

				var p = Process.Start(psi);

				new StreamReader2Line()
				{
                    Target = new FuncDecorator<string>(s => DateTime.Now + " " + s)
                    {
                        Target = new Splitter<string>(
                                new Labeler(arg + " Out: ") { Target = new ConsoleTarget() }, 
                                new TextWriterTarget(new StreamWriter(arg + "-out.log"))
                        )
                    }
				}.On(p.StandardOutput);

				new StreamReader2Line()
				{                    
					Target = new Labeler(arg + " Err: ")
					{
						Target = new ConsoleTarget()
					}
				}.On(p.StandardError);
			}
//			target.Target = 

			string line;
			while ((line = Console.ReadLine()) != null)
			{
				console.On(line);
			}
		}
	}

	interface IEmployee
	{
		string Name
		{
			get;
			set;
		}

		int Counter
		{
			get;
		}
	}

	public class Employee : IEmployee
	{
		public static int numberOfEmployees;

		private string name;

		public string Name  // read-write instance property
		{
			get
			{
				return name;
			}
			set
			{
				name = value;
			}
		}

		private int counter;

		public int Counter  // read-only instance property
		{
			get
			{
				return counter;
			}
		}

		public Employee()  // constructor
		{
			counter = ++counter + numberOfEmployees;
		}
	}

	class TestEmployee
	{
		static void Main2()
		{
			System.Console.Write("Enter number of employees: ");
			Employee.numberOfEmployees = int.Parse(System.Console.ReadLine());

			Employee e1 = new Employee();
			System.Console.Write("Enter the name of the new employee: ");
			e1.Name = System.Console.ReadLine();

			System.Console.WriteLine("The employee information:");
			System.Console.WriteLine("Employee number: {0}", e1.Counter);
			System.Console.WriteLine("Employee name: {0}", e1.Name);
		}
	}
}
