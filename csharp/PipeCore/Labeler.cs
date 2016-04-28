using System;
using System.IO;
using System.Diagnostics;
using System.Collections;

namespace Pipes
{
//	class LineLabeler : StreamReader
//	{
//		public LineLabeler(Stream stream)
//			: base(stream)
//		{
//		}
//
//		public override string ReadLine()
//		{
//			return base.ReadLine();
//		}
//
//		public override async string ReadLineAsync()
//		{			
//			return await base.ReadLineAsync();
//		}
//	}

	public class Labeler : AbstractConverter<string, string>
	{
		string Label = "ok: ";

		public Labeler(string label)
		{
			this.Label = label;
		}

		public override void On(string value)
		{
			Target.On(this.Label + value);
		}
	}
	
}
