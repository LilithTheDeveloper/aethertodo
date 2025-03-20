package de.vakyria.aethertodo.database.types

import android.net.sip.SipErrorCode.IN_PROGRESS


public enum class Status(val value: String) {
  OPEN("open"),
  IN_PROGRESS("in_progress"),
  DONE("done"),
}