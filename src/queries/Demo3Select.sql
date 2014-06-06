SELECT COUNT(Stopword.stopwordId)
FROM Abstract, Stopword
WHERE Abstract.stopwordId = Stopword.stopwordId AND wordcount <> 50
