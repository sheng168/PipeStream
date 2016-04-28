using System;
using System.IO;

namespace Pipes
{

    public class FuncDecorator<T> : AbstractDecorator<T>
    {
        Func<T,T> func = var1 => var1;

        public FuncDecorator(Func<T, T> func)
        {
            this.func = func;
        }
        
        public override void On(T value)
        {
            base.On(func(value));
        }
    }

}
