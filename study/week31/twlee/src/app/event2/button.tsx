'use client'

import { MouseEventHandler, ReactNode } from "react"

export default function Button({ onClick, children } : { onClick: MouseEventHandler, children: ReactNode }) {
  return (
    <button onClick={onClick}>
      { children }
    </button>
  )
}