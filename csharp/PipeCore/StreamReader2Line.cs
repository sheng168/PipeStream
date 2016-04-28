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
		}
	}
	
}
