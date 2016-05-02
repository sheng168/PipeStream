using System;
using System.Threading.Tasks;

namespace Pipes
{
    
    public class DelayDecorator<T> : AbstractDecorator<T>
    {
        public async override void On(T value)
        {
            await Task.Delay(1);
            Target.On(value);
        }
    }
}
