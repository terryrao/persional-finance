import scala.collection.generic.CanBuildFrom
import scala.collection.{SeqLike, LinearSeq}

/**
  * Created by terryrao on 2016/4/16.
  */

object kk extends App {
  val x = Seq(1, 2, 30, -2, 20, 1, 3, 4)
  x.tails map (_.take(2))

  sealed trait BinaryTree[+A]

  case object NilTree extends BinaryTree[Nothing]

  case class Leaf[+A](value: A) extends BinaryTree[A]

  case class Branch[+A](value: A, left: BinaryTree[A], right: BinaryTree[A]) extends BinaryTree[A];

  def traverse[A, U](t: BinaryTree[A])(f: A => U): Unit = {

    def traverseHelper(current: BinaryTree[A], next: LinearSeq[BinaryTree[A]]): Unit = {
      current match {
        case Branch(value, left, right) =>
          f(value)
          traverseHelper(left, right +: next)
        case Leaf(value) if !next.isEmpty =>
          f(value)
          traverseHelper(next.head, next.tail)
        case Leaf(value) => f(value)
        case NilTree if !next.isEmpty =>
          traverseHelper(next.head, next.tail)
        case NilTree => ()
      }

    }
    traverseHelper(t, LinearSeq());

  }

  val a = Branch(1, Leaf(2), Branch(3, Leaf(4), NilTree))
  traverse(a)(println)
  Map(1 -> "a");

  object NaiveQuickSort2 {
    def sort[T] (a : Iterable[T]) (implicit n : Ordering[T]) : Iterable[T] = {
      if (a.size < 2) a
      else {
        import n._
        val pivot = a.head
        sort(a.filter(_ < pivot)) ++ sort(a.filter( _ == pivot)) ++ sort(a.filter(_ > pivot))
      }
    }

  }



  object QuickSortBetter {
    def sort[T,Coll](a : Coll)(implicit ev0: Coll <:< SeqLike[T,Coll],
      cbf:CanBuildFrom[Coll,T,Coll],n:Ordering[T]) :Coll = {
        if (a.size < 2) a
      else {
          val pivot = a.head
          import n._
          sort(a.filter( _ < pivot )) ++ sort(a.filter(_ == pivot)) ++ sort(a.filter(_ > pivot))
        }
    }

  }

}