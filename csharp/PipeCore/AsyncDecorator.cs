using System;
using System.Threading.Tasks;

namespace Pipes
{
    public class AsyncDecorator<T> : AbstractDecorator<T>
    {
        public async override void On(T value)
        {
            Target.On(value);
        }
    }
}
