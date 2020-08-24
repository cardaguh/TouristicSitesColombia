package co.cyclopsapps.sitiosturisticoscolombia

import org.mockito.ArgumentCaptor

/**
 * Created by Carlos Daniel Agudelo on 23/08/2020.
 */

fun <T> capture(argumentCaptor: ArgumentCaptor<T>) : T = argumentCaptor.capture()