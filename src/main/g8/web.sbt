import com.typesafe.sbt.web.SbtWeb.autoImport._
import com.typesafe.sbt.less.Import.LessKeys

unmanagedResourceDirectories in Test := Seq(baseDirectory.value / "target" / "web" / "public" / "test") //<+= baseDirectory(_ / "target/web/public/test")

pipelineStages := Seq(uglify, cssCompress, digest, gzip)

LessKeys.compress in Assets := true

includeFilter in(Assets, LessKeys.less) := "*.less"

DigestKeys.algorithms += "sha1"

excludeFilter in gzip := (excludeFilter in gzip).value || new SimpleFileFilter(file => new File(file.getAbsolutePath + ".gz").exists)

//includeFilter in closure := (includeFilter in closure).value && new SimpleFileFilter(f => f.getName.contains("play-stats"))

includeFilter in cssCompress := (includeFilter in cssCompress).value && new SimpleFileFilter(f => f.getName.contains(name))
