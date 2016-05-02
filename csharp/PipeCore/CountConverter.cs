//using System;
//using System.IO;
//using System.Diagnostics;
//using System.Collections;

namespace Pipes
{
    public class CountConverter<T> : AbstractConverter<T, int>
	{
        private int count;

		public override void On(T value)
		{
            count += 1;
			Target.On(count);
		}
	}

}
