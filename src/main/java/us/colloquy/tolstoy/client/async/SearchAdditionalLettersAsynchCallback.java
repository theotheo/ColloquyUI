package us.colloquy.tolstoy.client.async;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import us.colloquy.tolstoy.client.TolstoyConstants;
import us.colloquy.tolstoy.client.TolstoyMessages;
import us.colloquy.tolstoy.client.model.LetterDisplay;
import us.colloquy.tolstoy.client.model.ServerResponse;
import us.colloquy.tolstoy.client.util.CommonFormatter;

/**
 * Created by Peter Gershkovich on 12/25/17.
 */
public class SearchAdditionalLettersAsynchCallback implements AsyncCallback<ServerResponse>
{
    private static final TolstoyConstants constants = GWT.create(TolstoyConstants.class);

    private TolstoyMessages messages = GWT.create(TolstoyMessages.class);

    private Label feedback;

    VerticalPanel lettersContainer;

    Hidden totalNumberOfLoadedLetters;

    Image loadingProgressImage;

    public SearchAdditionalLettersAsynchCallback(VerticalPanel lettersContainerIn, Label feedbackIn, Hidden totalNumberOfLoadedLettersIn, Image loadingProgressImageIn)
    {

        feedback = feedbackIn;
        lettersContainer = lettersContainerIn;
        totalNumberOfLoadedLetters=totalNumberOfLoadedLettersIn;
        loadingProgressImage = loadingProgressImageIn;
    }

    @Override
    public void onFailure(Throwable caught)
    {
        feedback.setText(constants.retrievalError());

        loadingProgressImage.setVisible(false);
    }

    @Override
    public void onSuccess(ServerResponse result)
    {
        loadingProgressImage.setVisible(false);
        //get total number of records

        //note that here we are not deleting any letters just adding them
        CommonFormatter.formatLetterDisplay(result, lettersContainer);

        totalNumberOfLoadedLetters.setValue((Integer.valueOf(totalNumberOfLoadedLetters.getValue()) + result.getLetters().size()) +"");


        feedback.setText( messages.numberOfLetterFound(result.getTotalNumberOfLetters() + "", totalNumberOfLoadedLetters.getValue()));


    }
}
