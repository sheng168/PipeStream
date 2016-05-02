//using System;
using System.IO;
//using System.Diagnostics;
//using System.Collections;

namespace Pipes
{

    public class StreamReader2Line : AbstractConverter<StreamReader, string>
    {
        public async override void On(StreamReader value)
        {
            while (true)
            {
                var line = await value.ReadLineAsync();
                if (line == null)
                    break;
                Target.On(line);
            }
            Target.Dispose();
        }
    }

}
