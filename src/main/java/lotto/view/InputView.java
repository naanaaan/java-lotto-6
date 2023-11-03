package lotto.view;

import java.util.Arrays;
import java.util.List;

import lotto.domain.Lotto;
import lotto.io.InputViewReader;
import lotto.util.message.ErrorMessage;
import lotto.util.validator.Validator;

public class InputView {

	private static final InputViewReader READER = new InputViewReader();
	private static final String DELIMITER = ",";

	public int inputMoney() {
		String inputValue = READER.readLine();

		validateInputMoney(inputValue);

		return Integer.parseInt(inputValue);
	}

	public List<Integer> inputAnswerLottoNumbers() {
		String inputValue = READER.readLine();
		String[] inputValues = inputValue.split(DELIMITER);

		List<Integer> numbers = convertInputValuesToCollection(inputValues);

		validateInputAnswerLottoNumbers(numbers);

		return convertStrArrToCollection(inputValues);
	}

	public int inputBonusNumber(List<Integer> numbers) {
		String inputValue = READER.readLine();

		validateInputBonusNumber(numbers, inputValue);

		return Integer.parseInt(inputValue);
	}

	private void validateInputMoney(String inputValue) {
		validateisNumberFormat(inputValue);
	}

	private List<Integer> convertInputValuesToCollection(String[] inputValues) {
		Validator.validateStrArrLength(inputValues, Lotto.LOTTO_SIZE,
				ErrorMessage.INPUT_ANSWER_LOTTO_NUMBER_ERROR.getForMatMessage(Lotto.LOTTO_SIZE));

		for (String inputValue : inputValues) {
			validateisNumberFormat(inputValue);
		}

		return convertStrArrToCollection(inputValues);
	}

	private List<Integer> convertStrArrToCollection(String[] strArr) {
		return Arrays.stream(strArr).map(Integer::valueOf)
				.toList();
	}

	private void validateInputAnswerLottoNumbers(List<Integer> numbers) {
		int[] numberRange = { Lotto.MIN_NUMBER, Lotto.MAX_NUMBER };

		validateAnswerLottoNumbers(numbers, numberRange);
	}

	private void validateAnswerLottoNumbers(List<Integer> numbers, int[] numberRange) {
		Validator.validateSize(numbers, Lotto.LOTTO_SIZE,
				ErrorMessage.LOTTO_SIZE_ERROR.getForMatMessage(Lotto.LOTTO_SIZE));
		Validator.validateDuplicateNumber(numbers, ErrorMessage.LOTTO_NUMBER_DUPLICATE_ERROR.getMessage());
		Validator.validateNumbersOutOfRange(numbers, numberRange,
				ErrorMessage.LOTTO_NUMBER_RANGE_ERROR.getForMatMessage(numberRange[0], numberRange[1]));
	}

	private void validateInputBonusNumber(List<Integer> numbers, String inputValue) {
		int[] numberRange = { Lotto.MIN_NUMBER, Lotto.MAX_NUMBER };
		Integer number;

		validateisNumberFormat(inputValue);

		number = Integer.parseInt(inputValue);

		validateBonusNumber(numbers, number, numberRange);
	}

	private void validateBonusNumber(List<Integer> numbers, Integer number, int[] numberRange) {
		Validator.validateNumbersOutOfRange(number, numberRange,
				ErrorMessage.LOTTO_NUMBER_RANGE_ERROR.getForMatMessage(numberRange[0], numberRange[1]));
		Validator.validateNumberInNumbers(numbers, number,
				ErrorMessage.LOTTO_BONUS_NUMBER_DUPLICATE_ERROR.getMessage());
	}

	private void validateisNumberFormat(String inputValue) {
		Validator.validateIsEmpty(inputValue, ErrorMessage.INPUT_EMPTY_ERROR.getMessage());
		Validator.validateNumberFormat(inputValue, ErrorMessage.INPUT_LETTER_ERROR.getMessage());
	}
}
