SELECT COUNT(*)
FROM Stopword, Abstract
WHERE Stopword.stopwordId = Abstract.stopwordId AND Stopword.stopwordId = Abstract.source
