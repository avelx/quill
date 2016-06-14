package io.getquill.sources.jdbc.mysql

import io.getquill._
import io.getquill.sources.sql.PeopleSpec

class PeopleJdbcSpec extends PeopleSpec {

  override def beforeAll = {
    val t = testMysqlDB.transaction { transactional =>
      transactional.run(query[Couple].delete)
      transactional.run(query[Person].filter(_.age > 0).delete)
      transactional.run(peopleInsert)(peopleEntries)
      transactional.run(couplesInsert)(couplesEntries)
    }
  }

  "Example 1 - differences" in {
    testMysqlDB.run(`Ex 1 differences`) mustEqual `Ex 1 expected result`
  }

  "Example 2 - range simple" in {
    testMysqlDB.run(`Ex 2 rangeSimple`)(`Ex 2 param 1`, `Ex 2 param 2`) mustEqual `Ex 2 expected result`
  }

  "Example 3 - satisfies" in {
    testMysqlDB.run(`Ex 3 satisfies`) mustEqual `Ex 3 expected result`
  }

  "Example 4 - satisfies" in {
    testMysqlDB.run(`Ex 4 satisfies`) mustEqual `Ex 4 expected result`
  }

  "Example 5 - compose" in {
    testMysqlDB.run(`Ex 5 compose`)(`Ex 5 param 1`, `Ex 5 param 2`) mustEqual `Ex 5 expected result`
  }

  "Example 6 - predicate 0" in {
    testMysqlDB.run(satisfies(eval(`Ex 6 predicate`))) mustEqual `Ex 6 expected result`
  }

  "Example 7 - predicate 1" in {
    testMysqlDB.run(satisfies(eval(`Ex 7 predicate`))) mustEqual `Ex 7 expected result`
  }

  "Example 8 - contains empty" in {
    testMysqlDB.run(`Ex 8 and 9 contains`)(`Ex 8 param`) mustEqual `Ex 8 expected result`
  }

  "Example 9 - contains non empty" in {
    testMysqlDB.run(`Ex 8 and 9 contains`)(`Ex 9 param`) mustEqual `Ex 9 expected result`
  }
}
