'use client'

import Button from "./button"

export default function CustomButton({ text } : { text: string}) {

  function handleSampleClick() {
    alert('custom!')
  }
  return (
    <Button onClick={handleSampleClick}>
      Sample &quot;{text}&quot; Click!
    </Button>
  )
}