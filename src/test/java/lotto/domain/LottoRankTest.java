package lotto.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class LottoRankTest {

	@DisplayName("일치한 로또 번호 수와 보너스 번호 유뮤에 따른 로또 상금을 확인한다.")
	@MethodSource("createGetLottoRankTestParameter")
	@ParameterizedTest
	void checkGetLottoRankMethodReturnValue(AnswerLotto answer, Lotto lotto, int rankPrice) {
		int lottoPrice = LottoRank.getMatchedLottoRank(answer, lotto).getPrice();

		assertEquals(lottoPrice, rankPrice);
	}

	static Stream<Arguments> createGetLottoRankTestParameter() {
		return Stream.of(
				Arguments.of(new AnswerLotto(List.of(1, 2, 3, 4, 5, 6), 7), new Lotto(List.of(8, 9, 10, 11, 12, 13)),
						LottoRank.NOTHING.getPrice()),
				Arguments.of(new AnswerLotto(List.of(1, 2, 3, 4, 5, 6), 7), new Lotto(List.of(1, 2, 9, 10, 11, 12)),
						LottoRank.NOTHING.getPrice()),
				Arguments.of(new AnswerLotto(List.of(1, 2, 3, 4, 5, 6), 7), new Lotto(List.of(1, 2, 3, 10, 11, 12)),
						LottoRank.FIFTH.getPrice()),
				Arguments.of(new AnswerLotto(List.of(1, 2, 3, 4, 5, 6), 7), new Lotto(List.of(1, 2, 3, 4, 11, 12)),
						LottoRank.FOURTH.getPrice()),
				Arguments.of(new AnswerLotto(List.of(1, 2, 3, 4, 5, 6), 7), new Lotto(List.of(1, 2, 3, 4, 5, 12)),
						LottoRank.THIRD.getPrice()),
				Arguments.of(new AnswerLotto(List.of(1, 2, 3, 4, 5, 6), 7), new Lotto(List.of(1, 2, 3, 4, 5, 7)),
						LottoRank.SECOND.getPrice()),
				Arguments.of(new AnswerLotto(List.of(1, 2, 3, 4, 5, 6), 7), new Lotto(List.of(1, 2, 3, 4, 5, 6)),
						LottoRank.FIRST.getPrice()));
	}
}
