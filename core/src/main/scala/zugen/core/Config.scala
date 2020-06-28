package zugen.core

import java.nio.file.{Files, Path, Paths}

import zugen.core.Config.{ClassesPath, DocumentPath, DocumentsToGenerate, DomainPackageName}

final case class Config(
  classesPath: ClassesPath, // ドキュメント生成対象プロジェクトのクラスファイルが格納されたディレクトリへのパス
  domainPackages: Seq[DomainPackageName], // ドメインオブジェクトのパッケージ名
  documentsToGenerate: DocumentsToGenerate, // 生成するドキュメント種別
  documentPath: DocumentPath // 生成したドキュメントを配置するパス
)

object Config {

  final case class ClassesPath(value: Path) extends AnyVal
  object ClassesPath {
    def apply(value: String): ClassesPath = ClassesPath(Paths.get(value))
  }
  final case class DomainPackageName(value: String) extends AnyVal
  final case class DocumentsToGenerate(genDocTypes: Seq[GenDocumentType])
  final case class DocumentPath(value: Path) extends AnyVal {
    def exists: Boolean = Files.exists(value)
  }
  object DocumentPath {
    def apply(value: String): DocumentPath = DocumentPath(Paths.get(value))
  }

  /**
    * ドキュメント種別
    */
  sealed trait GenDocumentType

  object GenDocumentType {
    case object GenDomainObjectTable extends GenDocumentType
    case object GenDomainRelationDiagram extends GenDocumentType
  }

}
