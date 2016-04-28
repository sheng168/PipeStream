﻿using System;
//using System.IO;
using System.Collections.Generic;

namespace Pipes
{
    public interface ITarget<T> : IDisposable
    {
        void On(T value);
    }

    public interface ISource<T> : IDisposable
    {
        ITarget<T> Target
        {
            get;
            set;
        }
    }

    public interface IConverter<I, O> : ITarget<I>, ISource<O>
    {

    }

    /// <summary>
    /// Specialize non-Converter
    /// </summary>
    public interface IDecorator<T> : IConverter<T,T>
    {

    }

    public abstract class AbstractTarget<T> : ITarget<T>
    {
        public abstract void On(T value);

        public virtual void Dispose()
        {
            // noop default implementation
        }
    }

    public class NoopTarget<T> : AbstractTarget<T>
    {
        public override void On(T value)
        {
            // noop, use to terminate pipeline
        }

//        static ITarget<?> NOOP = new NoopTarget<?>();
    }

    public class AbstractSource<T> : ISource<T>
    {
//        protected AbstractSource(ITarget<T> target)
//        {
//            Target = target;
//        }

        public ITarget<T> Target
        {
            get;
            set;
        }

        public void Dispose()
        {
            Target.Dispose();
        }
    }

    public class Splitter<T>: AbstractTarget<T>
    {
        System.Collections.Generic.List<ITarget<T>> Targets;

        public Splitter(List<ITarget<T>> targets)
        {
            this.Targets = targets;
        }

        public Splitter(params ITarget<T>[] targets)
        {
            Targets = new List<ITarget<T>>(targets);
        }

        public override void On(T value)
        {
            foreach (var t in Targets)
            {
                t.On(value);
            }
        }

        public override void Dispose()
        {
            foreach (var t in Targets)
            {
                t.Dispose();
            }
        }
    }

    public abstract class AbstractConverter<T, S> : AbstractSource<S>, IConverter<T, S>
    {
//        public AbstractPipe(ITarget<S> target) : base(target)
//        {
//        }
        
        public abstract void On(T value);
    }

    public abstract class AbstractDecorator<T> : AbstractConverter<T,T> 
    {
        public override void On(T value)
        {
            Target.On(value);
        }
    }

}
