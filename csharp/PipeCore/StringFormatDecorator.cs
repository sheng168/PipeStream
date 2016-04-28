using System;
using System.IO;

namespace Pipes
{

    public class StringFormatDecorator<S> : AbstractConverter<S, string>
    {
//        Func<string,string> myFunc = var1 => "value: " + var1;

        readonly string format;
        readonly object[] args;

        /// <summary>
        /// Initializes a new instance of the <see cref="Pipes.StringFormatDecorator`1"/> class.
        /// </summary>
        /// <param name="format">Format.</param>
        /// <param name="args">Arguments. args[0] will be replaced with On(value)</param>
        public StringFormatDecorator(string format, object[] args)
        {
            this.format = format;
            this.args = args;
        }

        public override void On(S value)
        {
            args[0] = value;
            Target.On(string.Format(format: format, args:args));
        }
    }

}
