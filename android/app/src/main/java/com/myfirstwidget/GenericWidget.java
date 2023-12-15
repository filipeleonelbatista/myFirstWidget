package com.myfirstwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.RemoteViews;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Implementation of App Widget functionality.
 */
public class GenericWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        try{
            SharedPreferences sharedPref = context.getSharedPreferences("DATA", context.MODE_PRIVATE);
            String stringJsonData = sharedPref.getString("myData", "{\"message\":'Olá mundo', \"updatedAt\": 1702645634574}");
            JSONObject widgetData = new JSONObject(stringJsonData);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.generic_widget);

            long timestamp = Long.parseLong(widgetData.getString("updatedAt")); // Convertendo a string para long

            Date data = new Date(timestamp); // Criando um objeto Date a partir do timestamp

            SimpleDateFormat formatoDesejado = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            String dataFormatada = formatoDesejado.format(data);

            views.setTextViewText(R.id.messageValue, widgetData.getString("message"));
            views.setTextViewText(R.id.updatedAtValue, dataFormatada);

            appWidgetManager.updateAppWidget(appWidgetId, views);


        }catch(JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}