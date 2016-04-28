using System;
using System.IO;
using System.Diagnostics;
using System.Collections;

namespace Pipes
{
    public class ConsoleTarget : TextWriterTarget
	{
        public ConsoleTarget() : base(Console.Out)
        {
        }
	}
}
