using System;
using System.IO;

namespace Pipes
{

    public class CountDecorator<T> : AbstractDecorator<T>
    {
        public int Count
        {
            get;
            set;
        }

        public override void On(T value)
        {
            Count++;
            base.On(value);
        }
    }

}
