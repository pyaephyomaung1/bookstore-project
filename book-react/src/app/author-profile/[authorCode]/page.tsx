'use client'
import { useParams } from "next/navigation";
import { AuthorComponent } from "@/app/author-profile/[authorCode]/AuthorComponent";

export default function AuthorProfilePage() {
    const params = useParams();
    const authorCode = params.authorCode as string;

    return (
        <>
            <AuthorComponent authorCode={authorCode} />
        </>
    );
}
