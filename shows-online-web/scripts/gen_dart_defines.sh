#!/usr/bin/env bash
# Prints (on one line) --dart-define flags for the Firebase Web app config.
#
# Uses FIREBASE_API_KEY / FIREBASE_APP_ID from the environment when both are set
# (e.g. GitHub repo variables); otherwise reads them with the Firebase CLI, which
# must already be authenticated (GOOGLE_APPLICATION_CREDENTIALS or `firebase login`).
# These are public web identifiers, not secrets.
set -euo pipefail

PROJECT="${FIREBASE_PROJECT:-shows-online-d3274}"

if [[ -z "${FIREBASE_API_KEY:-}" || -z "${FIREBASE_APP_ID:-}" ]]; then
  APP_ARG=()
  [[ -n "${FIREBASE_WEB_APP_ID:-}" ]] && APP_ARG=("${FIREBASE_WEB_APP_ID}")
  json="$(npx --yes firebase-tools apps:sdkconfig WEB "${APP_ARG[@]}" --project "$PROJECT" --json)"
  eval "$(printf '%s' "$json" | python3 -I -c '
import json, shlex, sys
cfg = json.loads(json.load(sys.stdin)["result"]["fileContents"])
print("FIREBASE_API_KEY=" + shlex.quote(cfg["apiKey"]))
print("FIREBASE_APP_ID=" + shlex.quote(cfg["appId"]))
print("FIREBASE_MEASUREMENT_ID=" + shlex.quote(cfg.get("measurementId", "")))
print("FIREBASE_STORAGE_BUCKET=" + shlex.quote(cfg.get("storageBucket", "")))
')"
fi

flags=("--dart-define=FIREBASE_API_KEY=${FIREBASE_API_KEY}" "--dart-define=FIREBASE_APP_ID=${FIREBASE_APP_ID}")
[[ -n "${FIREBASE_MEASUREMENT_ID:-}" ]] && flags+=("--dart-define=FIREBASE_MEASUREMENT_ID=${FIREBASE_MEASUREMENT_ID}")
[[ -n "${FIREBASE_STORAGE_BUCKET:-}" ]] && flags+=("--dart-define=FIREBASE_STORAGE_BUCKET=${FIREBASE_STORAGE_BUCKET}")
echo "${flags[*]}"
