//using System;

namespace Pipes
{
    public class SyncLockDecorator<T> : AbstractDecorator<T>
    {
        private object thisLock = new object();

        public override void On(T value)
        {
            lock (thisLock)
            {
                base.On(value);
            }
        }
    }
}
