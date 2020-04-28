package gui;

import journal.*;

import java.util.ArrayList;
import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.*;

public class Pages extends Application{
		public void start(Stage primaryStage) {
			Journal j = new Journal();
			//Entry entry; 
			ArrayList<Dream> drms = new ArrayList<Dream>();
			
			//common Buttons
			Button returnToTitle = new Button("Go Back");
			Button returnToTitle2 = new Button("Go Back");
			Button returnToTitle3 = new Button("Go Back w/o Saving");
			//first page
			Rectangle rect = new Rectangle();
			rect.setWidth(25);
			rect.setHeight(10);
			rect.setArcWidth(10);
			rect.setArcHeight(10);
			Button newEntry = new Button("Create a new Entry");
			Button viewSummary = new Button("View Summary");
			Button viewStats = new Button("View General Stats");
			newEntry.setShape(rect);
			viewSummary.setShape(rect);
			viewStats.setShape(rect);
			
			VBox FirstPageButtons = new VBox(new Label("What do you wish to do?"), newEntry, viewSummary, viewStats);
			FirstPageButtons.setSpacing(25);
			FirstPageButtons.setAlignment(Pos.CENTER);
			FirstPageButtons.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
		
			Scene scene = new Scene(FirstPageButtons, 240, 360);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Dream Journal 0.0.1");
			primaryStage.show();
			
			//newEntry Page
			Button newDream = new Button("Add A Dream");
			Button closeEntry = new Button("Close Entry");
			ComboBox<Integer> sleepHour = new ComboBox<Integer>();
			ComboBox<Integer> sleepMinute = new ComboBox<Integer>();
			
			ComboBox<Integer> wakeHour = new ComboBox<Integer>();
			ComboBox<Integer> wakeMinute = new ComboBox<Integer>();
			
			for (int i = 0; i < 24; i++) {
				sleepHour.getItems().add(i);
				wakeHour.getItems().add(i);
			}
			for (int i = 0; i < 60; i++ ) {
				sleepMinute.getItems().add(i);
				wakeMinute.getItems().add(i);
			}
			
			ComboBox<Integer> months = new ComboBox<Integer>(); 
			ComboBox<Integer> days = new ComboBox<Integer>();
			
			for (int i = 1; i <= 12; i ++) 
				months.getItems().add(i);
			for (int i = 1; i <= 31; i ++) 
				days.getItems().add(i);
			
			TextField years = new TextField();
			years.setPrefColumnCount(5);
			
			VBox newEntryFirstCol = new VBox(new Label("Date:"), new Label("Time Fallen Asleep:   "), new Label("Time Woken Up"));
			VBox newEntrySecCol = new VBox(months, sleepHour, wakeHour);
			VBox newEntryThirdCol = new VBox(days, sleepMinute, wakeMinute);
			VBox newEntry4thCol = new VBox(years);
			HBox newEntryHB = new HBox(newEntryFirstCol, newEntrySecCol, newEntryThirdCol, newEntry4thCol);
			newEntryHB.setSpacing(5);
			HBox newEntryButtons = new HBox(newDream, closeEntry, returnToTitle3);
			VBox newEntryVB = new VBox(newEntryHB, newEntryButtons);
			newEntryVB.setSpacing(10);
			newEntryVB.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
			Scene newEntryScene = new Scene(newEntryVB);
			
			//newDream Page
			TextArea dream = new TextArea();
			Button saveDream = new Button("Save Dream");
			
			VBox newDreamVB = new VBox(dream, saveDream);
			newDreamVB.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
			Scene newDreamScene = new Scene(newDreamVB, 360, 360);

			//viewSummary Page
			VBox summaryVB = new VBox();
 
			ArrayList<Button> viewEntryButtons = new ArrayList<Button>();
			ArrayList<Label> viewEntryLabels = new ArrayList<Label>();
			for(int i = 0; i < j.getNumEntries(); i ++) {
				viewEntryLabels.add(new Label(j.viewEntry(i)));
				viewEntryButtons.add(new Button("View Entry"));
				summaryVB.getChildren().add(new HBox(5, new Label(j.viewEntrySummary(i)), viewEntryButtons.get(i)));
			}
			for (int i = 0; i < j.getNumEntries(); i++) {
				int i2 = i;
				viewEntryButtons.get(i).setOnAction(e -> {
					Label entryText = new Label(j.viewEntry(i2));
					Button returnToTitle4 = new Button("Return to Title");
					returnToTitle4.setOnAction(e2-> {
						primaryStage.setScene(scene);
					});
					VBox viewEntryVB = new VBox(entryText, returnToTitle4);
					viewEntryVB.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
					Scene viewEntryScene = new Scene(viewEntryVB);
					primaryStage.setScene(viewEntryScene);
				});
			}
			
			summaryVB.getChildren().add(returnToTitle);
			summaryVB.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
			Scene viewSummaryScene = new Scene(summaryVB);
			
			//viewStats Page
			VBox viewStatsVB = new VBox(new Label(j.getStats()), returnToTitle2);
			viewStatsVB.setBackground(new Background(new BackgroundFill(Color.LAVENDER, CornerRadii.EMPTY, Insets.EMPTY)));
			Scene viewStatsScene = new Scene(viewStatsVB);
			
		
		
			//Common buttons
			returnToTitle.setOnAction(e -> {
				primaryStage.setScene(scene);
			});
			returnToTitle2.setOnAction(e -> {
				primaryStage.setScene(scene);
			});
			returnToTitle3.setOnAction(e -> {
				primaryStage.setScene(scene);
			});
			
			//first page buttons
			newEntry.setOnAction(e -> {
				primaryStage.setScene(newEntryScene);
			});
			viewSummary.setOnAction(e -> {
				primaryStage.setScene(viewSummaryScene);
			});
			viewStats.setOnAction(e -> {
				primaryStage.setScene(viewStatsScene);
			});
			
			//newEntry Page Buttons
			newDream.setOnAction(e -> {
				primaryStage.setScene(newDreamScene);
			});
			closeEntry.setOnAction(e -> {
				Time fallenAsleep = new Time((int) sleepHour.getValue(), (int)sleepMinute.getValue());
				Time wokenUp = new Time((int) wakeHour.getValue(), (int) wakeMinute.getValue());
				int m = (int)months.getValue();
				int d = (int) days.getValue();
				int y = Integer.parseInt(years.getText());
				Date date = new Date(d, m, y);
				Entry entry = new Entry(date, fallenAsleep, wokenUp);
				for (int i = 0; i < drms.size(); i ++ )
					entry.addDream(drms.get(i));
				j.makeEntry(entry);
				drms.clear();
				j.closeJournal();
				
				primaryStage.setScene(scene);
			});
			
			//dream page buttons
			saveDream.setOnAction(e-> {
				 drms.add(new Dream(dream.getText()));
				 dream.clear();
				 primaryStage.setScene(newEntryScene);
			});
		}
		public static void main(String [] args) {
				Application.launch(args);
		}
}
