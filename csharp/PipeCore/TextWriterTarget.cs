using System;
using System.IO;
using System.Diagnostics;
using System.Collections;

namespace Pipes
{
	public class TextWriterTarget : AbstractTarget<string>
    {
        TextWriter writer;

        public TextWriterTarget(TextWriter writer)
        {
            this.writer = writer;
        }        

        public override void On(string value)
        {
            writer.WriteLine(value);
            writer.Flush(); // so content will show up timely for tailing file
        }

        public override void Dispose()
        {
            writer.Dispose();
            base.Dispose();
        }
    }

}
