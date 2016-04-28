using System;
using System.IO;

namespace Pipes
{

    public class LogDecorator<T> : AbstractDecorator<T>
    {
        readonly TextWriter writer;

        public LogDecorator(TextWriter writer = null)
        {
            if (writer == null)
                this.writer = Console.Out;
            else
                this.writer = writer;
        }
        
        public override void On(T value)
        {
            writer.WriteLineAsync(value.ToString());
            base.On(value);
        }
    }

}
