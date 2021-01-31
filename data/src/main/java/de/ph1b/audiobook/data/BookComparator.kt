package de.ph1b.audiobook.data

import de.ph1b.audiobook.common.comparator.NaturalOrderComparator

private val byName = Comparator<Book> { left, right ->
  NaturalOrderComparator.stringComparator.compare(left.name, right.name)
}
private val byAuthor = Comparator<Book> { left, right ->
  NaturalOrderComparator.stringComparator.compare(left.author, right.author)
}
private val byLastPlayed = compareByDescending<Book> {
  it.content.settings.lastPlayedAtMillis
}

private val byAddedAt = compareByDescending<Book> { it.metaData.addedAtMillis }

enum class BookComparator(private val comparatorFunction: Comparator<Book>) :
  Comparator<Book> by comparatorFunction {
  BY_LAST_PLAYED((byLastPlayed).then(byName)),
  BY_NAME(byName.then(byLastPlayed)),
  BY_DATE_ADDED(byAddedAt.then(byName)),
  BY_DATE_AUTHOR_AND_NAME(byAuthor.then(byName.then(byLastPlayed)));
}
